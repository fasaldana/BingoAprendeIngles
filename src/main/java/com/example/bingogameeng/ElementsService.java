package com.example.bingogameeng;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
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
                    new Elements(1, "Chicken"),
                    new Elements(2, "Dog"),
                    new Elements(3, "Cow"),
                    new Elements(4, "Bull"),
                    new Elements(5, "Goat"),
                    new Elements(6, "Pig")
            }).stream()
            .collect(Collectors.toConcurrentMap(s -> s.getId(), Function.identity()));

    // DB id sequence mock
    private AtomicLong sequence = new AtomicLong(3);

    public List<Elements> readAll() {
        Map<Long, Elements> data = new HashMap<>();
        try {
            String fila = "";
            File doc = new File("C:/users/Fernando/Desktop/elementos.txt");
            FileReader fileReader = new FileReader(doc);
            BufferedReader reader = new BufferedReader(fileReader);
            int cont = 1;
            while((fila=reader.readLine())!=null){
                data.put(Long.valueOf(cont+""),new Elements(Long.valueOf(cont+""), fila));
                cont++;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return data.values().stream().collect(Collectors.toList());
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

    public void writeFile(){
        try {
            int cont = 1;
            while(true) {
                if(cont>repository.size()){
                    break;
                }
                File doc = new File("C:/users/Fernando/Desktop/elementos.txt");
                //doc.createNewFile();
                FileWriter myWriter = new FileWriter(doc);
                myWriter.write("");
                for(int i=1; i<=cont; i++){
                    myWriter.append(repository.get(Long.parseLong(i+"")).getEleName()+"\n");
                }
                myWriter.flush();
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
                Thread.sleep(15000);
                cont++;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        repository.remove(id);
    }
}