package pl.spiralarchitect.kplan.core.smoke;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;

/**
 * Base deployment specification for Arquillian
 * 
 * @author mjazwinski
 *
 */
public class ArquillianDeploymentSpec {

	
    @Deployment
    public static EnterpriseArchive createDeployment() {
    	/**
    	 * Do not specify all dependencies here
    	 * it will be hell, let maven take care of it
    	 */
		PomEquippedResolveStage resolver = Maven.resolver().loadPomFromFile("pom.xml");
    	/** ShrinkWrap will resolve dependecies from pom.xml */
    	File[] dependencies = resolver.importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();
    	/** crate ejb-jar archive */
        JavaArchive ejbJar = ShrinkWrap.create(JavaArchive.class, "kplan-core-1.0.0-SNAPSHOT.jar")
        		/** here we add packages - first parameter tells ShrikWrap if it should inlclude nested packages also */
			    .addPackages(true, Filters.includeAll(), "pl.spiralarchitect.kplan");
			    /** For CDI 1.1 we no longer need to include beans.xml - scanning will be done in annotated mode */
			    //.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        
        /** I create ear as I will probably extend this to ear deployment later */
        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, "kplan.ear");
        ear.setApplicationXML("application.xml");
        ear.addAsLibraries(dependencies);
        ear.addAsModule(ejbJar);

        return ear;
    }
}
