package pl.javaskills.creditapp.core.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.ProcessedCreditApplication;

import java.io.*;
import java.text.MessageFormat;

import static pl.javaskills.creditapp.core.utils.Constants.OUTPUT_PATH;

public class FileManager {
    private static final Logger log = LoggerFactory.getLogger(FileManager.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static void write(ProcessedCreditApplication processedCreditApplication){
        String processId = processedCreditApplication.getCreditApplication().getId();

        File folder = new File(OUTPUT_PATH);
        File fileToSave = new File(OUTPUT_PATH +"/" +processId+".dat");
        if(!folder.exists())
            folder.mkdir();
        //zapis obiektu
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileToSave))) {
            out.writeObject(processedCreditApplication);
            log.info("Save credit application with decision to: {}",fileToSave.getAbsolutePath());
        } catch (IOException e) {
            fileToSave.deleteOnExit();
            log.error("File save has failed: ", e);
        }
    }

    public static ProcessedCreditApplication read(String processId){
        File fileToRead = new File(OUTPUT_PATH +"/"+ processId+".dat");
        if(fileToRead.exists()){
            //odczyt obiektu
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileToRead))) {
                return (ProcessedCreditApplication) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                log.error("File read has failed: ", e);
                throw new IllegalStateException(e.getMessage());
            }
        } else {
            log.error(MessageFormat.format("Credit file {0} doesn't exists!", fileToRead.getName()));
            throw new IllegalStateException(MessageFormat.format("Credit file {0} doesn't exists!", fileToRead.getName()));
        }
    }
}
