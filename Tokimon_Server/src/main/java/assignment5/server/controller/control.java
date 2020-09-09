/**
 * Control class controls the POST GET and DELETE requests on the server and also manages reading and writing
 * the JSon file.
 *
 * @author  Jovanjot Bapla
 */


package assignment5.server.controller;
import assignment5.server.tokis.toki;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class control {
    ArrayList<toki> tokis= new ArrayList<>();
    private int count;

    @DeleteMapping("DELETE/api/tokimon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToki(@PathVariable int id)throws IOException {
        System.out.println("DELETE");
        boolean found = false;
        for(int i=0; i<tokis.size(); i++){
            if(tokis.get(i).getId()==id){
                tokis.remove(i);
                found=true;
            }
        }
        if(!found){
            throw new notFoundError("TOKIMON NOT FOUND");
        }
        objectJson();
    }

    @GetMapping("GET/api/tokimon/all")
    public List<toki> getTokis(){
        System.out.println("GET");
        return tokis;
    }

    @GetMapping("GET/api/tokimon/{id}")
    public toki getTokiId(@PathVariable int id){
        for(int i=0; i<tokis.size(); i++){
            if(tokis.get(i).getId()==id){
                return tokis.get(i);
            }

        }
        throw new notFoundError("TOKIMON NOT FOUND");
    }

    @PostMapping("POST/api/tokimon/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addToki(@RequestBody toki toki)throws IOException {
        int max=-1;
        if(tokis.size()>0) {
            for (toki obj : tokis) {
                if(obj.getId()>=max){
                    max=obj.getId();
                }
            }
        }
        max++;
        toki.setId(max);
        tokis.add(toki);
        objectJson();
        System.out.println("POST");
    }


    @PostConstruct
    public void init()throws IOException{
        try {
            readJson();
        }
        catch (NullPointerException e){
            objectJson();
        }

    }

public void objectJson()throws IOException{
    Gson gson;
    gson = new GsonBuilder().setPrettyPrinting().create();
    String strJson = gson.toJson(tokis);
    try {
        FileWriter writer = new FileWriter("Data/tokimons.json");
        writer.write(strJson);
        writer.close();
    }
    catch (FileNotFoundException e) {
        new File("Data").mkdir();
        FileWriter writer = new FileWriter("Data/tokimons.json");
        writer.write("[]");
        writer.close();
    }
}

public void readJson() throws IOException {
    try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("data/tokimons.json"));
        toki[] tokiList = new Gson().fromJson(bufferedReader, toki[].class);
        for (toki tokiObj : tokiList) {
            tokis.add(tokiObj);
        }
    }
    catch (FileNotFoundException e){
        new File("Data").mkdir();
        FileWriter writer = new FileWriter("Data/tokimons.json");
        writer.write("[]");
        writer.close();
    }
    }
}
