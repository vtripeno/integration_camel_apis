FROM rabbitmq:3-management
MAINTAINER Victor Tripeno
COPY /docker/config/rabbitmq.config /etc/rabbitmq/rabbitmq.config
COPY /docker/config/definitions.json /opt/definitions.json
#EXPOSE 8080 5672 25676
RUN chown rabbitmq /etc/rabbitmq/rabbitmq.config && chgrp rabbitmq /etc/rabbitmq/rabbitmq.config