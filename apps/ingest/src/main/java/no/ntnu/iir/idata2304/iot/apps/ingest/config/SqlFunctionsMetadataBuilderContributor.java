package no.ntnu.iir.idata2304.iot.apps.ingest.config;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**
 * Custom SqlFunctionsMetadataBuilderContributor that adds the DATE_TRUNC function to Hibernate.
 * This is required for using the DATE_TRUNC function in JPQL based queries. 
 */
public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
  
  @Override
  public void contribute(MetadataBuilder metadataBuilder) {
    metadataBuilder.applySqlFunction(
      "DATE_TRUNC", 
      new StandardSQLFunction("DATE_TRUNC", StandardBasicTypes.TIMESTAMP)
    );
  }

}
