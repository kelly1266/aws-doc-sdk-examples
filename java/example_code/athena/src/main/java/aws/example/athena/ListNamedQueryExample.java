package aws.example.athena;

import com.amazonaws.services.athena.AmazonAthena;
import com.amazonaws.services.athena.model.ListNamedQueriesRequest;
import com.amazonaws.services.athena.model.ListNamedQueriesResult;

import java.util.List;

/**
 * ListNamedQueryExample
 * -------------------------------------
 * This code shows how to obtain a list of named query IDs.
 */
public class ListNamedQueryExample
{
    public static void main(String[] args) throws Exception
    {
        // Build an Athena client
        AthenaClientFactory factory = new AthenaClientFactory();
        AmazonAthena client = factory.createClient();

        // Build the request
        ListNamedQueriesRequest listNamedQueriesRequest = new ListNamedQueriesRequest();

        // Get the list results.
        ListNamedQueriesResult listNamedQueriesResult = client.listNamedQueries(listNamedQueriesRequest);

        // Process the results.
        boolean hasMoreResults = true;

        while (hasMoreResults) {
            List<String> namedQueryIds = listNamedQueriesResult.getNamedQueryIds();
            // process named query IDs
            
            // If nextToken is not null,  there are more results. Get the next page of results.
            if (listNamedQueriesResult.getNextToken() != null) {
                listNamedQueriesResult = client.listNamedQueries(
                        listNamedQueriesRequest.withNextToken(listNamedQueriesResult.getNextToken()));
            }
            else {
                hasMoreResults = false;
            }
        }
    }
}
