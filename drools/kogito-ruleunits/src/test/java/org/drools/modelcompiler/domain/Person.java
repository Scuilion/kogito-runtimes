/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.modelcompiler.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.core.phreak.AbstractReactiveObject;
import org.kie.api.definition.type.Position;

public class Person extends AbstractReactiveObject {

    @Position(0)
    private String name;

    @Position(1)
    private int age;

    private long ageLong;

    private Address address;
    private int id = 0;
    private String likes;
    private Boolean employed;

    private List<Address> addresses = new ArrayList<>();

    private BigDecimal money;

    private BigInteger ageInSeconds;

    private Map<Integer, Integer> items = new HashMap<>();

    public static int countItems(Map<?, ?> items) {
        return items.size();
    }

    public static boolean evaluate(Map<?, ?> items) {
        return items.size() > 0;
    }

    private int numberOfItems;

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    private Person ParentP;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, BigDecimal money) {
        this.name = name;
        this.money = money;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public Integer getAgeBoxed() {
        return age;
    }

    public Short getAgeAsShort() {
        return (short) age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyModification();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getAgeLong() {
        return ageLong;
    }

    public Person setAgeLong(long ageLong) {
        this.ageLong = ageLong;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public Boolean getEmployed() {
        return employed;
    }

    public void setEmployed(Boolean employed) {
        this.employed = employed;
    }

    /**
     * WARN: this toString() implementation is actually used in some tests.
     */
    @Override
    public String toString() {
        return name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public Person setMoney(BigDecimal money) {
        this.money = money;
        return this;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void addAddress(final Address address) {
        addresses.add(address);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(final List<Address> addresses) {
        this.addresses = addresses;
    }

    public Person getParentP() {
        return ParentP;
    }

    public Person setParentP(Person parentP) {
        ParentP = parentP;
        return this;
    }

    public BigInteger getAgeInSeconds() {
        return ageInSeconds;
    }

    public Person setAgeInSeconds(BigInteger ageInSeconds) {
        this.ageInSeconds = ageInSeconds;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Person person = (Person) o;
        return age == person.age && name.equals(person.name);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}