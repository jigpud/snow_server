package com.jigpud.snow.util.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageData <T> {
    private List<T> records;
    private long pages;
    private long current;

    public static <T> PageData<T> fromPage(Page<T> origin) {
        return new PageData<>(
                origin.getRecords(),
                origin.getPages(),
                origin.getCurrent()
        );
    }

    public static <IN, OUT> PageData<OUT> fromPage(Page<IN> origin, RecordsMapper<IN, OUT> mapper) {
        return new PageData<>(
                origin.getRecords().stream().map(mapper::map).collect(Collectors.toList()),
                origin.getPages(),
                origin.getCurrent()
        );
    }

    public static <IN, OUT> PageData<OUT> fromPageData(PageData<IN> origin, RecordsMapper<IN, OUT> mapper) {
        return new PageData<>(
                origin.getRecords().stream().map(mapper::map).collect(Collectors.toList()),
                origin.getPages(),
                origin.getCurrent()
        );
    }

    @FunctionalInterface
    public interface RecordsMapper<IN, OUT> {
        OUT map(IN in);
    }
}
