# 소켓을 사용하기 위해서는 socket을 import해야 한다.
import socket;
from _thread import *;
# binder함수는 서버에서 accept가 되면 생성되는 socket 인스턴스를 통해 client로 부터 데이터를 받으면 echo형태로 재송신하는 메소드이다.

class read:
    data = []
    index = 0
    # 현재 읽는 중인 위치
    def readByte(self):
        res = self.data[index]
        index = index+1
        return res
        #바이트1단위 읽고 index 현재위치를 반환
    def readInt(self):
        res = self.readByte() + (self.readByte() << 8) + (self.readByte() << 16) + (self.readByte() << 24)
        return res
        #4바이트읽고 little 엔디안으로 한다음 출력
    def readShort(self):
        res = self.readByte() + (self.readByte() << 8)
        return res
        #2바이트읽고 little 엔디안으로 한다음 출력
    def readString(self):
        res = self.readShort()
        return res
    def checkData(self):
        type = self.readByte()
        return type
        #2바이트읽고 출력
    def __init__(self,data):
        self.data = data


class write:
    msg = []

    def writeByte(self,data):
        msg.append(data)
        #리스트에 1바이트
    def writeInt(self,data):
        self.writeByte(data & 0xff)
        msg.append((data >> 8 & 0xff))
        msg.append((data >> 16 & 0xff))
        msg.append((data >> 24 & 0xff))

    def writeString(self,length):
        msg.append((data & 0xff))
        msg.append((data >> 8 & 0xff))

    def bufferReturn(self):
        return msg



def binder(client_socket, addr):
    print('Connected by', addr);
    try:
        # 접속 상태에서는 클라이언트로 부터 받을 데이터를 무한 대기한다.
        # 만약 접속이 끊기게 된다면 except가 발생해서 접속이 끊기게 된다.
        while True:
            buf = b''
            byteData = client_socket.recv(1)
            print(byteData)
            data = read(byteData)
            type = data.checkData()
            print(type)
            '''
            if type == 0:
                #카메라
                print(0)
            elif type == 1:
                #조도
                print(1)
            elif type == 2:
                #기울기
                print(2)
            data.readByte()
            msg = write(data)'''
    except:
    # 접속이 끊기면 except가 발생한다.
        print("except : " , addr);
    finally:
    # 접속이 끊기면 socket 리소스를 닫는다.
        client_socket.close();
# 소켓을 만든다.
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM);
# 소켓 레벨과 데이터 형태를 설정한다.
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1);
# 서버는 복수 ip를 사용하는 pc의 경우는 ip를 지정하고 그렇지 않으면 None이 아닌 ''로 설정한다.
# 포트는 pc내에서 비어있는 포트를 사용한다. cmd에서 netstat -an | find "LISTEN"으로 확인할 수 있다.


server_socket.bind(('127.0.0.1', 1234));
# server 설정이 완료되면 listen를 시작한다.
server_socket.listen();



try:
    # 서버는 여러 클라이언트를 상대하기 때문에 무한 루프를 사용한다.
    while True:
        # client로 접속이 발생하면 accept가 발생한다.
        # 그럼 client 소켓과 addr(주소)를 튜플로 받는다.
        client_socket, addr = server_socket.accept();
        start_new_thread(binder, (client_socket,addr));
except:
    print("error");
finally:
    # 에러가 발생하면 서버 소켓을 닫는다.
 server_socket.close();
