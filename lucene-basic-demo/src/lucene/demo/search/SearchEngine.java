/*
 * SearchEngine.java
 *
 * Created on 6 March 2006, 14:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lucene.demo.search;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author John
 */
public class SearchEngine {
    private IndexSearcher searcher = null;
    private QueryParser parser = null;
    
    /** Creates a new instance of SearchEngine */
    public SearchEngine() throws IOException {
        searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get("index-directory"))));
        parser = new QueryParser("content", new StandardAnalyzer());
    }
    
    public TopDocs performSearch(String queryString, int n)
    throws IOException, ParseException {
        Query query = parser.parse(queryString);        
        return searcher.search(query, n);
    }

    public Document getDocument(int docId)
    throws IOException {
        return searcher.doc(docId);
    }
}
