FROM anapsix/alpine-java

ARG id
ARG count

COPY ./target/SDConsistency-1.0-SNAPSHOT.jar /home/jar.jar
COPY ./target/dependency-jars /home/dependency-jars
#CMD java -jar /home/jar.jar $id 808$id $count
RUN java -jar /home/jar.jar $id 808$id $count
#RUN touch /home/aa$id.txt
#RUN touch /home/aa$count.txt

