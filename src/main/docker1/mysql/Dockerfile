FROM mariadb

ENV MYSQL_DATA_DIR=/var/lib/mysql \
    MYSQL_RUN_DIR=/run/mysqld \
    MYSQL_LOG_DIR=/var/log/mysql


#RUN mysql -u root -p$MYSQL_ROOT_PASSWORD  -e "create database exchange;"
ADD init_db.sh /home/init_db.sh
#RUN /home/init_db.sh
