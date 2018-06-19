#!/usr/bin/env bash
sh ./kill.sh;
principal=0
numservers=4
mvn clean package -DskipTests;
for i in `seq 0 $numservers`; do
    gnome-terminal --title="PORT: 808$i" --working-directory=WORK_DIR -x bash -c "cd $(pwd);java -jar -Dserver.port=808$i -Dspring.profiles.active=profile$i target/Consistency-0.0.1-SNAPSHOT.jar $i 808$i $numservers; bash"
done
