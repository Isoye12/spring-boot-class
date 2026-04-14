# Build Stage
FROM bellsoft/liberica-openjdk-alpine:21 AS builder

# 2. 컨테이너 내부 작업 폴더 설정
WORKDIR /app/

# 3. 코드를 app 폴더로 복사(내 컴퓨터 로컬 파일을 컨테이너에 복사하다)
COPY . .

# 4. 컨테이너에 복사된 코드로 실행파일(.jar) 생성
RUN ./gradlew clean build -x test

# Run Stage
FROM bellsoft/liberica-openjdk-alpine:21

# 컨테이너 내부 작업 폴더 설정
WORKDIR /app

# 실행할때 .jar만 복사해서 실행
