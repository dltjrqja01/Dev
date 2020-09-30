import urllib.request
from bs4 import BeautifulSoup

url = "https://maple.gg/u/"
x = input()

req = urllib.request.urlopen(url+x)
res = req.read()

soup = BeautifulSoup(res, 'html.parser')  # BeautifulSoup 객체생성
keywords = soup.find_all('li', class_='user-summary-item')  # 데이터에서 태그와 클래스를 찾는 함수
print(keywords)
