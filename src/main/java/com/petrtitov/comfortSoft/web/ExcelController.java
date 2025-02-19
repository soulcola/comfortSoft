package com.petrtitov.comfortSoft.web;

import com.petrtitov.comfortSoft.util.ExcelUtils;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.petrtitov.comfortSoft.util.QuickSelect.quickSelect;

@RestController
@RequestMapping("/api")
@Validated
public class ExcelController {

    @GetMapping("/findNthMax")
    public Integer findNthMax(
            @RequestParam @NotBlank(message = "Путь к файлу не должен быть пустым") String filePath,
            @RequestParam @Min(value = 1, message = "Параметр n должен быть не меньше 1") int n) throws IOException {

        List<Integer> numbers = ExcelUtils.readNumbersFromXlsx(filePath);

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("В файле не найдено числовых значений.");
        }
        if (n > numbers.size()) {
            throw new IllegalArgumentException("Параметр n должен быть от 1 до " + numbers.size());
        }

        int[] nums = numbers.stream().mapToInt(Integer::intValue).toArray();
        // Преобразуем задачу в поиск (k)-го наименьшего элемента, где k = nums.length - n
        int k = nums.length - n;
        return quickSelect(nums, 0, nums.length - 1, k);
    }
}