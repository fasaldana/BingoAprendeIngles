package com.example.bingogameeng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/elements")
@CrossOrigin(origins ="http://localhost:3000")
public class ElementController {

    @Autowired
    private ElementsService service;

    @GetMapping("/")
    public List<Elements> read() {
        return service.readAll();
    }

    @GetMapping("/start")
    public void start() {
        service.writeFile();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Elements> read(@PathVariable("id") Long id) {
        Elements foundStudent = service.read(id);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundStudent);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Elements> create(@RequestBody Elements student) throws URISyntaxException {
        Elements createdStudent = service.create(student);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdStudent.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdStudent);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Elements> update(@RequestBody Elements element, @PathVariable Long id) {
        Elements updatedStudent = service.update(id, element);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedStudent);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
