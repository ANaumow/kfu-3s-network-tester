package ru.naumow;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        new Test().launch();
    }

    private void launch() throws IOException {
        String json = Files.lines(new File("src/main/resources/test-seti/questions.json").toPath()).reduce("", (x, y) -> x + y);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue(json, ObjectNode.class).get("questions");
        List<Question> questionList = new ArrayList<>();
        for (JsonNode jsonNode : node) {
            Question question = mapper.convertValue(jsonNode, Question.class);
            questionList.add(question);
        }
        Collections.shuffle(questionList);
        List<String> answersList = new ArrayList<>(questionList.size());
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println(i + ") " + questionList.get(i).getQuestion());
            answersList.add(scanner.nextLine());
        }

        for (int i = 0; i < questionList.size(); i++) {
            System.out.println("---------");
            System.out.println(i + ") " + questionList.get(i).getQuestion());
            System.out.println("your answers are:");
            System.out.println(answersList.get(i));
            System.out.println("answers are:");
            System.out.println(questionList.get(i).getAnswers());
        }


    }

}
