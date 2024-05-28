package com.uri.pw.taskmanager.controllers;

import com.uri.pw.taskmanager.entities.Task;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TaskControllerTest extends AbstractController {
    @Autowired
    protected MockMvc mvc;

    @Test
    public void getAll() throws Exception {
        String uri = "/tasks";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                    .get(uri)
                    .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Task[] taskList = super.mapFromJson(content, Task[].class);

        assertTrue(taskList.length > 0);
    }

    @Test
    public void getById() throws Exception {
        String uri = "/tasks/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Task task = super.mapFromJson(content, Task.class);
        assertEquals(1, task.getId());
    }

    @Test
    public void create() throws Exception {
        String uri = "/tasks";
        Task task = new Task();
        task.setDescription("Teste");
        task.setDone(false);
        task.setCreationDate(new Date(2024, 5, 30));
        task.setLimitDate(new Date(2024, 5, 31));
        String body = super.mapToJson(task);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Task response = super.mapFromJson(content, Task.class);
        assertEquals("Teste", response.getDescription());
    }

    @Test
    public void update() throws Exception {
        String uri = "/tasks/1";

        Task task = new Task();
        task.setDescription("Teste atualizado");
        task.setDone(false);
        task.setCreationDate(new Date(2024, 5, 30));
        task.setLimitDate(new Date(2024, 6, 10));

        String body = super.mapToJson(task);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Task response = super.mapFromJson(content, Task.class);
        assertEquals("Teste atualizado", response.getDescription());
    }

    @Test
    public void delete() throws Exception {
        String uri = "/tasks/3";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .delete(uri))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }

    @Test
    public void markAsDone() throws Exception {
        String patchUri = "/tasks/2/finish";
        String getUri = "/tasks/2";

        MvcResult mvcPatchResult = mvc.perform(MockMvcRequestBuilders
                        .patch(patchUri))
                .andReturn();

        int status = mvcPatchResult.getResponse().getStatus();
        assertEquals(204, status);

        MvcResult mvcGetResult = mvc.perform(MockMvcRequestBuilders
                        .get(getUri)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcGetResult.getResponse().getContentAsString();
        Task response = super.mapFromJson(content, Task.class);
        assertEquals(true, response.getDone());
    }
}
