package com.endava.internship.mocking.service;

import com.endava.internship.mocking.model.Payment;
import com.endava.internship.mocking.model.Status;
import com.endava.internship.mocking.model.User;
import com.endava.internship.mocking.repository.PaymentRepository;
import com.endava.internship.mocking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    ValidationService validationService;

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentService paymentService;

    @Test
    void createPayment_throwsIllegalArgumentExceptionIfIdNull() {
        doThrow(IllegalArgumentException.class).when(validationService).validateUserId(null);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> paymentService.createPayment(null, 12.0));
    }

    @Test
    void createPayment_returnsCorrectPayment() {
        User userForMock = new User(11, "Ion", Status.ACTIVE);
        Payment paymentForMock = new Payment(11, 12.111, "Payment from user Ion");

        when(userRepository.findById(11)).thenReturn(Optional.of(userForMock));
        when(paymentRepository.save(any())).thenReturn(paymentForMock);

        assertThat(paymentService.createPayment(11, 12.111)).isEqualTo(paymentForMock);
    }

    @Test
    void createPayment_throwsIllegalArgumentExceptionIfAmountNull() {
        doThrow(IllegalArgumentException.class).when(validationService).validateAmount(null);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> paymentService.createPayment(123, null));
    }

    @Test
    void createPayment_throwsNoSuchElementExceptionIfIdNotExistent() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> paymentService.createPayment(100, 12.0));
    }

    @Test
    void editPaymentMessage_throwsIllegalArgumentExceptionIfNullUUID() {
        doThrow(IllegalArgumentException.class).when(validationService).validatePaymentId(null);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> paymentService.editPaymentMessage(null, "test"));
    }

    @Test
    void editPaymentMessage_throwsIllegalArgumentExceptionIfNullMessage() {
        doThrow(IllegalArgumentException.class).when(validationService).validateMessage(null);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> paymentService.editPaymentMessage(UUID.randomUUID(), null));
    }

    @Test
    void getAllByAmountExceeding_thrownNullPointerExceptionIfNullAmount() {
        doReturn(Arrays.asList(new Payment(12, 12.2, "asd")))
            .when(paymentRepository).findAll();

        assertThatNullPointerException().isThrownBy(() -> paymentService.getAllByAmountExceeding(null));
    }

    @Test
    void getAllByAmountExceeding_returnsActualValues() {
        Payment p1 = new Payment(12, 12.2, "asd");
        Payment p2 = new Payment(12, 12.0, "asd");

        doReturn(Arrays.asList(p1, p2)).when(paymentRepository).findAll();

        assertThat(paymentService.getAllByAmountExceeding(12.1)).containsExactly(p1);
    }

}
