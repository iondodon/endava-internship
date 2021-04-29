package com.endava.internship.service;


import com.endava.internship.domain.Privilege;
import com.endava.internship.domain.User;
import com.endava.internship.service.UserService;
import com.endava.internship.service.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@SuppressWarnings("unchecked")
public class UserServiceImplTest {

    private UserService userService = new UserServiceImpl();

    private static final List<Privilege> ALL_PRIVILEGES = asList(Privilege.values());

    @Test
    void shouldReturnFirstNamesSortedDesc() {
        final User user1 = new User(1L, "John", "Doe", 26, ALL_PRIVILEGES);
        final User user2 = new User(2L, "Greg", "Smith", 30, ALL_PRIVILEGES);
        final User user3 = new User(3L, "Alex", "Smith", 13, ALL_PRIVILEGES);

        final List<String> sortedFirstNames =
                userService.getFirstNamesReverseSorted(asList(user1, user2, user3));

        assertThat(sortedFirstNames).containsExactly("John", "Greg", "Alex");
    }

    @Test
    void shouldSortUsersByAgeDescAndNameAsc() {
        final User user1 = new User(1L, "John", "Doe", 26, ALL_PRIVILEGES);
        final User user2 = new User(2L, "Greg", "Smith", 30, ALL_PRIVILEGES);
        final User user3 = new User(3L, "Alex", "Smith", 13, ALL_PRIVILEGES);
        final User user4 = new User(4L, "Andrew", "Hopkins", 30, ALL_PRIVILEGES);

        final List<User> sortedUsers =
                userService.sortByAgeDescAndNameAsc(asList(user1, user2, user3, user4));

        assertThat(sortedUsers).containsExactly(user4, user2, user1, user3);
    }

    @Test
    void shouldReturnDistinctPrivilegesForUsers() {
        final User createUser = new User(1L, "John", "Doe", 26, singletonList(Privilege.CREATE));
        final User updateUser = new User(2L, "Greg", "Smith", 30, singletonList(Privilege.UPDATE));
        final User deleteUser = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final List<Privilege> distinctPrivileges =
                userService.getAllDistinctPrivileges(asList(createUser, updateUser, deleteUser));

        assertThat(distinctPrivileges).containsExactlyInAnyOrder(Privilege.CREATE, Privilege.UPDATE, Privilege.DELETE);
    }

    @Test
    void shouldReturnUpdateUserWithAgeHigherThanGiven() {
        final User updateUser1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User updateUser2 = new User(2L, "Greg", "Smith", 30, singletonList(Privilege.UPDATE));
        final User deleteUser = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Optional<User> foundUser =
                userService.getUpdateUserWithAgeHigherThan(asList(updateUser1, updateUser2, deleteUser), 29);

        assertThat(foundUser).hasValueSatisfying(user ->
                assertThat(user).isEqualTo(updateUser2));
    }

    @Test
    void shouldReturnGroupedMapByNumberOfPrivileges() {
        final int ONE_PRIVILEGE = 1;
        final int TWO_PRIVILEGES = 2;
        final int FOUR_PRIVILEGES = 4;

        final User userWith2Privileges = new User(1L, "John", "Doe", 26, asList(Privilege.UPDATE, Privilege.CREATE));
        final User userWith4Privileges = new User(2L, "Greg", "Smith", 30, ALL_PRIVILEGES);
        final User userWith1Privileges1 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));
        final User userWith1Privileges2 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Map<Integer, List<User>> groupedMap =
                userService.groupByCountOfPrivileges(asList(userWith2Privileges, userWith4Privileges, userWith1Privileges1, userWith1Privileges1));

        assertThat(groupedMap)
                .containsExactly(
                        entry(ONE_PRIVILEGE, asList(userWith1Privileges1, userWith1Privileges2)),
                        entry(TWO_PRIVILEGES, singletonList(userWith2Privileges)),
                        entry(FOUR_PRIVILEGES, singletonList(userWith4Privileges)));
    }

    @Test
    void shouldReturnAverageAgeForUsers() {
        final int expectedAverage = 23;

        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Smith", 30, singletonList(Privilege.UPDATE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final double averageAge = userService.getAverageAgeForUsers(asList(user1, user2, user3));

        assertThat(averageAge).isEqualTo(expectedAverage);
    }

    @Test
    void shouldReturnMinusOneInsteadOfAverageForEmptyList() {
        final int expectedAverage = -1;
        final double averageAge = userService.getAverageAgeForUsers(emptyList());

        assertThat(averageAge).isEqualTo(expectedAverage);
    }

    @Test
    void shouldReturnMostFrequentLastName() {
        final String mostFrequentName = "Smith";

        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", mostFrequentName, 30, singletonList(Privilege.UPDATE));
        final User user3 = new User(3L, "Alex", mostFrequentName, 13, singletonList(Privilege.DELETE));

        final Optional<String> mostFrequentLastName =
                userService.getMostFrequentLastName(asList(user1, user2, user3));

        assertThat(mostFrequentLastName)
                .hasValueSatisfying(name -> assertThat(name).isEqualToIgnoringCase(mostFrequentName));
    }

    @Test
    void shouldReturnOptionalEmptyIfThereAreDistinctNumberOfLastNames() {
        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, singletonList(Privilege.UPDATE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Optional<String> mostFrequentLastName =
                userService.getMostFrequentLastName(asList(user1, user2, user3));

        assertThat(mostFrequentLastName).isEmpty();
    }

    @Test
    void shouldFilterListOfUsersByGivenConditions() {
        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Predicate<User> firstNameLongerThan3 = u -> u.getFirstName().length() > 3;
        final Predicate<User> numberOfPrivilegesBiggerThan2 = u -> u.getPrivileges().size() > 2;

        final List<User> filteredUsers =
                userService.filterBy(asList(user1, user2, user3), firstNameLongerThan3, numberOfPrivilegesBiggerThan2);

        assertThat(filteredUsers).containsExactly(user2);
    }

    @Test
    void shouldReturnTheSameListIfNoPredicatesProvided() {
        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final List<User> users = asList(user1, user2, user3);

        final List<User> filteredUsers =
                userService.filterBy(users);

        assertThat(filteredUsers).isEqualTo(users);
    }

    @Test
    void shouldConvertListOfUsersToCSVOfUsersLastNames() {
        final User user1 = new User(1L, "John", "Doe", 26, emptyList());
        final User user2 = new User(2L, "Greg", "Jonson", 30, emptyList());
        final User user3 = new User(3L, "Alex", "Smith", 13, emptyList());

        final String csv = userService.convertTo(asList(user1, user2, user3), ",", User::getLastName);

        assertThat(csv).isEqualTo("Doe,Jonson,Smith");
    }

    @Test
    void shouldReturnGroupedMapByPrivileges() {
        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Map<Privilege, List<User>> groupedMap = userService.groupByPrivileges(asList(user1, user2, user3));

        assertThat(groupedMap)
                .contains(
                        entry(Privilege.UPDATE, asList(user1, user2)),
                        entry(Privilege.CREATE, singletonList(user2)),
                        entry(Privilege.DELETE, asList(user2, user3))
                );
    }

    @Test
    void shouldReturnGroupedMapOfLastNamesAndEncounteredNumber() {
        final User user1 = new User(1L, "John", "Smith", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Map<String, Long> numberOfLastNames = userService.getNumberOfLastNames(asList(user1, user2, user3));

        assertThat(numberOfLastNames)
                .contains(entry("Smith", 2L), entry("Jonson", 1L));
    }

}