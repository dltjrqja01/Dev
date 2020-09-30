import urllib.request
from bs4 import BeautifulSoup

print('Beginning file download with urllib2...')

url = 'https://www.google.co.kr/search?q=asian+eyes&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiQvvap_8vpAhVQ62EKHQC9AkwQ_AUoAXoECAwQAw&biw=1536&bih=731'
req = urllib.request.Request(url)
res = urllib.request.urlopen(url).read()

soup = BeautifulSoup(res, 'html.parser')
soup = soup.find("div", class_="rg_i Q4LuWd tx8vtf")
# img의 경로를 받아온다
imgUrl = soup.find_all("img")["src"]

# urlretrieve는 다운로드 함수
# img.alt는 이미지 대체 텍스트 == 마약왕
urllib.request.urlretrieve(imgUrl, soup.find("img")["alt"] + '.jpg')