package eu.om.repository;

import com.ontotext.trree.OwlimSchemaRepository;
import java.io.File;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;

public class RepositoryFactory {

    public static OMRepository owlim(String location) {
        OwlimSchemaRepository sail = new OwlimSchemaRepository();
        Repository repository = new SailRepository(sail);

        sail.setParameter("build-ptsoc", "true");
        sail.setParameter("enable-optimization", "true");
        sail.setParameter("fts-memory", "0M");
        sail.setParameter("storage-folder", "bigowlim-store");
        sail.setParameter("repository-type", "file-repository");
        sail.setParameter("console-thread", "false");
        sail.setParameter("ruleset", "owl-horst");

        repository.setDataDir(new File(location));

        try {
            repository.initialize();
            return new OMRepository(repository);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
