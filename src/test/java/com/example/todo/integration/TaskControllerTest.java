package com.example.todo.integration;

import com.example.todo.entity.TaskEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(value = 1)
    void testAddTask_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TaskEntity task = TaskEntity.builder()
                .title("Test")
                .description("test again")
                .priority("low")
                .build();
        this.mockMvc.perform(post("/api/createTask").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("Task added successfully"));
    }

    @Test
    @Order(value = 2)
    void testAddTask_missingDetails_failure() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TaskEntity task = TaskEntity.builder()
                .title(null)
                .description(null)
                .priority("low")
                .build();
        this.mockMvc.perform(
                        post("/api/createTask").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(task)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string("Task has missing details"));
    }

    @Test
    @Order(value = 3)
    void testAddTask_lengthExceed_failure() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TaskEntity task = TaskEntity.builder()
                .title("zhfgasfsgxkwobkgiouacoqqsxdzyf")
                .description("rhsvhilumlsskouujbnznjwurmhmnonjqhauomfryrzpbxwxskkohghkinqbjisspzjumkuoxhhjjvdigjvnwotmqsgbnyjayvnaffucatgspkcppcrmyxbcijbxddthgjcsqbmztyikjushvmtfognoydryizaoacwesxsaetxjkwhkuwflujkqsbpzlwivpfxeqvjedk")
                .priority("low")
                .build();
        this.mockMvc.perform(
                        post("/api/createTask").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(task)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string("Task title or description exceeds the character limit"));
    }

    void mockTaskCreate() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TaskEntity task = TaskEntity.builder()
                .title("Test")
                .description("test again")
                .priority("low")
                .build();
        this.mockMvc.perform(post("/api/createTask").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("Task added successfully"));
    }
    @Test
    @Order(value = 4)
    void testDeleteTask_success() throws Exception {
//        mockTaskCreate();
        this.mockMvc.perform(
                        delete("/api/deleteTask/{id}", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("Task deleted successfully"));
    }

    @Test
    @Order(value = 5)
    void testGetAllTasks_success() throws Exception {
        int tasks = 5;
        while(tasks-->0) mockTaskCreate();
        this.mockMvc.perform(
                        get("/api/getTasks"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].title").value("Test"))
                .andExpect(jsonPath("$[0].description").value("test again"));
    }
}
