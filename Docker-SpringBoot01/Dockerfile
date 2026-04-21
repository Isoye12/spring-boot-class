# 여기 안의 코드는 조각난 것들을 조합하도록 할 것임.(라인별 or 특정 키워드별)

# Build Stage
FROM bellsoft/liberica-openjdk-alpine:21 AS builder

# 2. 컨테이너 내부 작업 폴더 설정
WORKDIR /app/

# 3. 코드를 app 폴더로 복사(내 컴퓨터 로컬 파일을 컨테이너에 복사하다)
COPY . .

# 4. 컨테이너에 복사된 코드로 실행파일(.jar) 생성
# 테스트 코드는 제외하겠다(테스트 코드를 굳이 실행 파일에 포함시킬 이유가 X)
RUN ./gradlew clean build -x test

# Run Stage
FROM bellsoft/liberica-openjdk-alpine:21

# 컨테이너 내부 작업 폴더 설정
WORKDIR /app

# 실행할때 .jar만 복사해서 실행
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트를 8080 쓴다는 것을 명시
EXPOSE 8080

# 컨테이너가 실행되자마자 생성된 .jar를 실행하도록 수정
ENTRYPOINT ["java", "-jar", "app.jar"]