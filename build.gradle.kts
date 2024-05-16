plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jaspersoft.jfrog.io/jaspersoft/third-party-ce-artifacts/" )

}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.beanio:beanio:2.1.0") // Replace with the latest version
//    implementation("org.springframework.boot:spring-boot-starter-web:3.2.5")
    implementation("org.projectlombok:lombok:1.18.26")
//    implementation("net.sf.jasperreports:jasperreports:6.8.0")
    implementation("net.sf.jasperreports:jasperreports:6.9.0")
    implementation("org.springframework:spring-core:5.3.13")
//    implementation("org.springframework.boot:3.0.8")
}

tasks.test {
    useJUnitPlatform()
}
