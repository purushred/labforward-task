package com.labforward.task.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labforward.task.constants.AppConstants;
import com.labforward.task.model.Note;
import com.labforward.task.model.NotesResponse;
import com.labforward.task.repository.interfaces.INotesRepository;
import com.labforward.task.service.interfaces.INotesService;

/**
 * 
 * @author Purushotham Yanamala
 *
 */
@Service
public class NotesService implements INotesService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private INotesRepository notesRepository;

    @Override
    public Note saveNotes(Note note) {
        note.setTime(new Date());
        note.setId(UUID.randomUUID().toString());
        return notesRepository.save(note);
    }

    @Override
    public List<Note> getNotes() throws IOException {

        Iterable<Note> iterable = notesRepository.findAll();
        List<Note> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    @Override
    public void deleteNotes() {
       notesRepository.deleteAll();
    }
    
    @Override
    public NotesResponse searchNotes(String searchStr) throws IOException {

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightDataField = new HighlightBuilder.Field(AppConstants.DATA_FIELD);
        highlightDataField.highlighterType("unified");
        highlightBuilder.field(highlightDataField);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.query(
                QueryBuilders.matchQuery(AppConstants.DATA_FIELD, searchStr).fuzziness(AppConstants.EDIT_DISTANCE));

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(AppConstants.INDEX_NAME);
        searchRequest.source(searchSourceBuilder);

        try {

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            int occurrences = 0;
            Set<String> similarWords = new TreeSet<>();
            Pattern pattern = Pattern.compile(AppConstants.REGEX_STR);
            for (SearchHit hit : hits.getHits()) {

                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField highlight = highlightFields.get(AppConstants.DATA_FIELD);
                Text[] fragments = highlight.fragments();
                String fragmentStr = fragments[0].string();
                Matcher matcher = pattern.matcher(fragmentStr);

                while (matcher.find()) {
                    String word = matcher.group(1);
                    if (searchStr.equals(word)) {
                        occurrences++;
                    } else {
                        similarWords.add(word);
                    }
                }
            }

            NotesResponse response = new NotesResponse();
            response.setOccurrences(occurrences);
            response.setSimilarWords(similarWords.toArray(String[]::new));

            return response;

        } catch (IOException ex) {
            throw ex;
        }
    }
}
