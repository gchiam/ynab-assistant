FROM  adoptopenjdk/openjdk8:slim
RUN mkdir /opt/app
COPY build/install/ynab-assistant /opt/app/ynab-assistant
COPY config /opt/app/ynab-assistant/config
WORKDIR /opt/app/ynab-assistant
CMD ["bin/ynab-assistant", "server", "config/default.yaml"]
