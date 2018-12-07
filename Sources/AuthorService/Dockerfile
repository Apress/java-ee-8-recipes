# Using the Payara Micro 5 snapshot build (previously pulled)
FROM payara/micro:5-SNAPSHOT
 
# Downloads the Apache Derby JDBC JAR
RUN wget -O $PAYARA_PATH/derbyclient-10.14.1.0.jar http://central.maven.org/maven2/org/apache/derby/derbyclient/10.14.1.0/derbyclient-10.14.1.0.jar
 
# Adds an application to be loaded
ADD AuthorService-1.0.war $PAYARA_PATH
 
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar", "--addJars", "/opt/payara/derbyclient-10.14.1.0.jar", "--deploy", "/opt/payara/AuthorService-1.0.war"]