package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Specializations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Main7 {
    public static void main(String[] args) {

        System.out.println(specializationDeadlines(2025));

    }

    public static String specializationDeadlines(int year) {

        String json = Api.getSpecializations(year);
        Specializations specializations = new Gson().fromJson(json, Specializations.class);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        String res = specializations.items.stream()
                .map(item -> item.deadline != null ? item.deadline.value : null)
                .filter(dateStr -> dateStr != null && !dateStr.equals("null"))
                .map(dateStr -> LocalDate.parse(dateStr.trim(), inputFormatter))
                .distinct()
                .sorted()
                .map(date -> date.format(inputFormatter))
                .collect(Collectors.joining(","));

        return res;

    }
}