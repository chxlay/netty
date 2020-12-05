package com.chxlay.thrift;

import com.chxlay.thrift.generate.GlobeException;
import com.chxlay.thrift.generate.Person;
import com.chxlay.thrift.generate.PersonService;

/**
 * 继承生成的Service下生成 Iface
 *
 * @author Alay
 * @date 2020-12-06 03:41
 * @project netty
 */
public class PersonServiceImpl implements PersonService.Iface {


    @Override
    public Person getById(byte id) throws org.apache.thrift.TException {
        System.out.println("getById():参数是：" + id);
        Person person = new Person();
        person.setId(id);
        person.setAge((short) 18);
        person.setName("张三");
        return person;
    }

    @Override
    public void save(Person person) throws GlobeException, org.apache.thrift.TException {
        System.out.println("save():参数是：" + person);
        System.out.println(person.getId());
        System.out.println(person.getName());
        System.out.println(person.getAge());
    }
}