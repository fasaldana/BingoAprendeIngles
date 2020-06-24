package com.example.bingogameeng;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ElementsService {

    // DB repository mock
    private Map<Long, Elements> repository = Arrays.asList(
            new Elements[]{
                    new Elements(1, "Cat"),
                    new Elements(2, "Dog"),
                    new Elements(3, "cow")
            }).stream()
            .collect(Collectors.toConcurrentMap(s -> s.getId(), Function.identity()));

    // DB id sequence mock
    private AtomicLong sequence = new AtomicLong(3);

    public List<Elements> readAll() {
        return repository.values().stream().collect(Collectors.toList());
    }

    public Elements read(Long id) {
        return repository.get(id);
    }

    public Elements create(Elements element) {
        long key = sequence.incrementAndGet();
        element.setId(key);
        repository.put(key, element);
        return element;
    }

    public Elements update(Long id, Elements element) {
        element.setId(id);
        Elements oldStudent = repository.replace(id, element);
        return oldStudent == null ? null : element;
    }

    public void delete(Long id) {
        repository.remove(id);
    }
}