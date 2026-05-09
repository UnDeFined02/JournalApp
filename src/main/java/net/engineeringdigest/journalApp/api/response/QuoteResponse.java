package net.engineeringdigest.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class QuoteResponse {
    private String quote;
    private String author;
    private List<String> categories;
}