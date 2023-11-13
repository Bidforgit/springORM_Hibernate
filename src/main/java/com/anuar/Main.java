package com.anuar;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class Main {

    /**
     * Spring IoC Container / Контекст  самостоятельно конструрирует,инициализурем,собирает все необходим обьекты для функциониров
     * бизнес-приложения базируяс на описанной мета-конфигурациеи xml формате
     */
    public static void main(String[] args) {
        /**
         * Создается контейнер, которому отдается файл содержащий мета конфигурацию.
         * На основании файла мета-конфигурации контейнер создает все необходимые объекты и связывает их
         * В том числе, этот процесс включает внедрение объектов - зависимостей в более сложные объекты, наприм. конструктор.
         */

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(SpringConfig.class);

//      Car car = context.getBean("car",Car.class);

        // Component
        CarShowRoom c2 = context2.getBean("carShowRoom",CarShowRoom.class);

        // через сеттер
        CarShowRoom c3 = new CarShowRoom();
//        c3.setCar(context.getBean("sUV",SUV.class));


        c2.showCar();


//        Student c1= context.getBean("student",Student.class);
//
//        System.out.println(c1);

        context2.close();

    }
}

// ВНЕДРЕНИЕ ЗАВИСИМОСТЕЙ (DEPEND INJECTION)
// конструктор  -----  Car car = context.getBean("car",Car.class);  ->>> carshowroom = new carshow()
// сеттер   @Setter ---> property name appContext
// файл
//
/**   аннотации Autowired  на Carshowroom классе passengercar
 *  @Setter(onMethod_ = {@Autowired})
 *     private PassengerCar car;

 *     @Autowired
 *     public CarShowRoom(PassengerCar car) {
 *         this.car = car;
 *     }
 */
interface Car {
    int speed();
}

@Component
class PassengerCar implements Car{

    @Override
    public int speed() {
        System.out.println("passengers car");
        return 123;
    }
}

@Component
class SUV implements Car {

    @Override
    public int speed() {
        System.out.println("SUVs car");
        return 90;
    }
}


@NoArgsConstructor
@Component
class CarShowRoom {

    //Ioc - слабая зависимость
    private Car car;
    private Car car2;

    public void showCar(){
        System.out.println("car speed is " + car.speed() + " ," + car2.speed());
    }

    public CarShowRoom(Car car, Car car2) {
        this.car = car;
        this.car2 = car2;
    }
}


@Configuration
@ComponentScan("com.anuar")
class SpringConfig {

    @Bean
    public PassengerCar passengerCar() {
        return new PassengerCar();
    }

    @Bean
    public SUV suv() {
        return new SUV();
    }

    @Bean
    public CarShowRoom carShowRoom() {
        return new CarShowRoom(passengerCar(),suv());
    }
}