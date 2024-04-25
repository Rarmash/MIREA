FROM ubuntu:20.04
RUN apt update \
	&& apt install -y python3 nano \
	&& cd /usr/bin \
	&& ln -s python3 python
ADD ./data/student.txt /mnt/files/student.txt
EXPOSE 80
CMD ["python" , "-m" , "http.server" , "-d" , "/mnt/" , "80"]
