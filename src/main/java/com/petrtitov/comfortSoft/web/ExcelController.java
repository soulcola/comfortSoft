package com.petrtitov.comfortSoft.web;

import com.petrtitov.comfortSoft.util.ExcelUtils;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.petrtitov.comfortSoft.util.QuickSelect.findNthMaxInList;

@RestController
@RequestMapping("/api")
@Validated
public class ExcelController {

    @GetMapping("/findNthMax")
    public Integer findNthMax(
            @Parameter(description = "Путь к локальному XLSX файлу", required = true)
            @RequestParam @NotBlank(message = "Путь к файлу не должен быть пустым") String filePath,
            @Parameter(description = "Порядковый номер максимального числа (N)", required = true)
            @RequestParam @Min(value = 1, message = "Параметр n должен >= 1") int n) throws IOException {

        List<Integer> numbers = ExcelUtils.readNumbersFromXlsx(filePath);

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("File has no numbers");
        }
        if (n > numbers.size()) {
            throw new IllegalArgumentException("n must be from 1 to " + numbers.size());
        }
        return findNthMaxInList(n, numbers);
    }
}