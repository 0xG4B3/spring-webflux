package com.joaogabee.springwebflux;

import com.joaogabee.springwebflux.controller.EmployeeController;
import com.joaogabee.springwebflux.dto.employeeDTO;
import com.joaogabee.springwebflux.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployee(){

        // given - pre-conditions or set up
        employeeDTO employeeDto = new employeeDTO();
        employeeDto.setFirstName("Test");
        employeeDto.setLastName("Testing");
        employeeDto.setEmail("testing@test.com");

        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(employeeDTO.class)))
                .willReturn(Mono.just(employeeDto));

        // when - action or behaviour
        WebTestClient.ResponseSpec response = webTestClient.post().uri("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(employeeDto), employeeDTO.class)
                .exchange();

        // then - verify the result or output
        response.expectStatus().isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
                .jsonPath("$.email").isEqualTo(employeeDto.getEmail());
    }

    @Test
    public void givenEmployeeId_whenGetEmployee_thenReturnEmployeeObject(){
        // given - pre-condition
        String employeeId = "123";

        employeeDTO employeeDto = new employeeDTO();
        employeeDto.setFirstName("Test");
        employeeDto.setLastName("Testing");
        employeeDto.setEmail("testing@test.com");

        BDDMockito.given(employeeService.getEmployee(employeeId))
                .willReturn(Mono.just(employeeDto));

        // when - action
        WebTestClient.ResponseSpec response = webTestClient.get()
                .uri("/api/employees/{id}", Collections.singletonMap("id", employeeId))
                .exchange();

        // then - verify the output
        response.expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
                .jsonPath("$.email").isEqualTo(employeeDto.getEmail());
    }

    @Test
    public void givenListOfEmployees_whenGetAllEmployees_returnListOfEmployees(){
        // given - pre-conditions or set up
        List<employeeDTO> list = new ArrayList<>();
        employeeDTO employeeDto1 = new employeeDTO();
        employeeDto1.setFirstName("Test");
        employeeDto1.setLastName("Testing");
        employeeDto1.setEmail("testing@test.com");
        list.add(employeeDto1);

        employeeDTO employeeDto2 = new employeeDTO();
        employeeDto2.setFirstName("Test");
        employeeDto2.setLastName("Testing");
        employeeDto2.setEmail("testing@test.com");
        list.add(employeeDto2);

        Flux<employeeDTO> employeeFlux = Flux.fromIterable(list);

        BDDMockito.given(employeeService.getAllEmployee())
                .willReturn(employeeFlux);

        // when - action or behaviour
        WebTestClient.ResponseSpec response = webTestClient.get().uri("/api/employees")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        // then - verify output of response
        response.expectStatus().isOk()
                .expectBodyList(employeeDTO.class)
                .consumeWith(System.out::println);
    }

    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdatedEmployeeObject(){

        // given - pre-conditions
        String employeeId = "123";

        employeeDTO employeeDto = new employeeDTO();
        employeeDto.setFirstName("Test");
        employeeDto.setLastName("Testing");
        employeeDto.setEmail("testing@test.com");

        BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(employeeDTO.class),
                        ArgumentMatchers.any(String.class)))
                .willReturn(Mono.just(employeeDto));

        // when - action or behaviour
        WebTestClient.ResponseSpec response = webTestClient.put().uri("/api/employees/{id}", Collections.singletonMap("id", employeeId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(employeeDto), employeeDTO.class)
                .exchange();


        // then - verify the result or output
        response.expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
                .jsonPath("$.email").isEqualTo(employeeDto.getEmail());
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing(){

        // given - pre-conditions
        String employeeId = "123";
        Mono<Void> voidMono = Mono.empty();
        BDDMockito.given(employeeService.deleteEmployee(employeeId))
                .willReturn(voidMono);

        // when - action or behaviour
        WebTestClient.ResponseSpec response = webTestClient
                .delete()
                .uri("/api/employees/{id}", Collections.singletonMap("id", employeeId))
                .exchange();

        // then - verify the output
        response.expectStatus().isNoContent()
                .expectBody()
                .consumeWith(System.out::println);
    }

}
