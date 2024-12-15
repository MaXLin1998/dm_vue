package jp.co.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jp.co.demo.model.SampleBean;
import jp.co.demo.model.UserModel;
import jp.co.demo.models.Model;
import jp.co.demo.models.Person;
import jp.co.demo.models.Pet;
import jp.co.demo.models02.Model02;
import jp.co.demo.models02.Person02;
import jp.co.demo.models02.Pet02;
import jp.co.demo.repository.UserRepository;
import jp.co.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public List<UserModel> findUsers(){
        return userRepository.findUsers();
    }

    @Override
    public UserModel getLoginUser(String username) {
//        SampleBean result1 = this.parseBean();
//        Model result1 = this.parseJsonToBean();
//        String json = this.parseBeanToJson();

        Model02 result1 = this.parseJsonToBean02();
        String json = this.parseBeanToJson02();

        return userRepository.findByUserId(username);
    }

    private SampleBean parseBean() {
        SampleBean result1 = null;

        // 準備
        String param = "{\"no\":100,\"data\":\"JSON変換文字列テスト\"}";

        try{
            result1 = ParseUtil.parseJsonToBeanByJackson(param);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result1;
    }

    private Model parseJsonToBean() {
        String json =
                "{" +
                        "    \"person\": {" +
                        "        \"firstName\": \"John\"," +
                        "        \"lastName\": \"Doe\"," +
                        "        \"address\": \"NewYork\"," +
                        "        \"pets\": [" +
                        "            {\"type\": \"Dog\", \"name\": \"Jolly\"}," +
                        "            {\"type\": \"Cat\", \"name\": \"Grizabella\"}," +
                        "            {\"type\": \"Fish\", \"name\": \"Nimo\"}" +
                        "        ]" +
                        "    }" +
                        "}";

        Gson gson = new Gson();
        Model model = gson.fromJson(json, Model.class);

        System.out.println("firstName:" + model.person.firstName);
        System.out.println("lastName:" + model.person.lastName);
        System.out.println("address:" + model.person.address);
        System.out.println("1st pet:" + model.person.pets.getFirst().name);

        return model;
    }

    private String parseBeanToJson(){
        Model model = new Model();
        model.person = new Person();
        model.person.firstName = "ジョン";
        model.person.lastName = "ドゥ";
        model.person.address = "ニューヨーク";

        model.person.pets = new ArrayList<Pet>();

        Pet pet1 = new Pet();
        pet1.type = "犬";
        pet1.name = "ジョリー";
        model.person.pets.add(pet1);

        Pet pet2 = new Pet();
        pet2.type = "猫";
        pet2.name = "グリザベラ";
        model.person.pets.add(pet2);

        Pet pet3 = new Pet();
        pet3.type = "魚";
        pet3.name = "ニモ";
        model.person.pets.add(pet3);

        Gson gson = new Gson();
        String json = gson.toJson(model);

        System.out.println(json);

        return json;
    }

    private Model02 parseJsonToBean02() {
        String json = "{" +
                "    \"person\": {" +
                "        \"firstName\": \"John\"," +
                "        \"lastName\": \"Doe\"," +
                "        \"address\": \"NewYork\"," +
                "        \"pets\": [" +
                "            {\"type\": \"Dog\", \"name\": \"Jolly\"}," +
                "            {\"type\": \"Cat\", \"name\": \"Grizabella\"}," +
                "            {\"type\": \"Fish\", \"name\": \"Nimo\"}" +
                "        ]" +
                "    }" +
                "}";

        Model02 model = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            model = mapper.readValue(json, Model02.class);

            System.out.println("firstName:" + model.person.firstName);
            System.out.println("lastName:" + model.person.lastName);
            System.out.println("address:" + model.person.address);
            System.out.println("1st pet:" + model.person.pets.getFirst().name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return model;
    }

    private String parseBeanToJson02(){
        Model02 model = new Model02();
        model.person = new Person02();
        model.person.firstName = "ジョン";
        model.person.lastName = "ドゥ";
        model.person.address = "ニューヨーク";
        model.person.pets = new ArrayList<Pet02>();
        Pet02 pet1 = new Pet02();
        pet1.type = "犬";
        pet1.name = "ジョリー";
        model.person.pets.add(pet1);
        Pet02 pet2 = new Pet02();
        pet2.type = "猫";
        pet2.name = "グリザベラ";
        model.person.pets.add(pet2);
        Pet02 pet3 = new Pet02();
        pet3.type = "魚";
        pet3.name = "ニモ";
        model.person.pets.add(pet3);

        String json = "";
        try{
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(model);
            System.out.println(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
