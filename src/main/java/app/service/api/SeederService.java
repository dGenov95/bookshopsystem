package app.service.api;

import java.io.IOException;

public interface SeederService {

    void seedData(String seedFilePath) throws IOException;

}
