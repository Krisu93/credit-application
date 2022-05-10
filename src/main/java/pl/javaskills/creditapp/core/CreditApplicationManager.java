package pl.javaskills.creditapp.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.javaskills.creditapp.CreditApplicationDecisionFactory;
import pl.javaskills.creditapp.core.io.FileManager;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.ProcessedCreditApplication;
import pl.javaskills.creditapp.di.Inject;

import java.util.ArrayDeque;
import java.util.Deque;

public class CreditApplicationManager {

    private static final Logger log = LoggerFactory.getLogger(CreditApplication.class);

    @Inject
    private CreditApplicationService service;
    @Inject
    private CreditApplicationDecisionFactory decisionFactory;
    private Deque<CreditApplication> applications = new ArrayDeque<>();

    public CreditApplicationManager() {

    }

    public void add(CreditApplication creditApplication) {
        applications.addLast(creditApplication);
        log.info("Application {} is added to queue.", creditApplication.getId());
    }

    public void startProcessing() {
        while (!applications.isEmpty()) {
            CreditApplication creditApplication = applications.pollFirst();
            log.info("Starting processing application with id {}.", creditApplication.getId());

            CreditApplicationDecision creditApplicationDecision = service.getDecision(creditApplication);
            String decision = decisionFactory.getDecision(creditApplicationDecision, creditApplication);
            log.info(decision);

            ProcessedCreditApplication processedCreditApplication = new ProcessedCreditApplication(creditApplication, decision);
            FileManager.write(processedCreditApplication);
            MDC.remove("id");
        }
    }

    public void loadApplication(String processId){
        ProcessedCreditApplication application = FileManager.read(processId);
        try {
            log.info(FileManager.OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(application));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
