FROM adoptopenjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/install/best-bottrop-api/ /app/
WORKDIR /app/bin
CMD ["./best-bottrop-api"]