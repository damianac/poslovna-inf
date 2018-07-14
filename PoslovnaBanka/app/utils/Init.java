package utils;

import models.*;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Init extends Job {

    public void doJob() {
        if(Mesto.count() == 0) {
            //sa fixtures,delete() se brise kes koji kontorlise
            //kojie je id da dobije entitet
            Fixtures.delete();
            Fixtures.loadModels("initial-data.yml");

        }
    }
}