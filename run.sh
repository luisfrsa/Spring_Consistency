#!/usr/bin/env bash
sh ./kill.sh;
principal=0
numservers=2
mvn clean package -DskipTests;
for i in `seq 0 $numservers`; do
    var=$i+1
    gnome-terminal --title="PORT: 808$i" --working-directory=WORK_DIR -x bash -c "cd /home/luis.alves/Documents/projetos/Spring_Consistency;java -jar -Dserver.port=808$i -Dspring.profiles.active=profile$var target/Consistency-0.0.1-SNAPSHOT.jar $i 808$i $numservers; bash"
done
