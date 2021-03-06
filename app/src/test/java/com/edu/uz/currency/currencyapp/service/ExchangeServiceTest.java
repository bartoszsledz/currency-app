package com.edu.uz.currency.currencyapp.service;

import com.edu.uz.currency.currencyapp.helper.RequestException;
import com.edu.uz.currency.currencyapp.model.Currency;
import com.edu.uz.currency.currencyapp.model.Money;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Java6Assertions.*;

public class ExchangeServiceTest {

    private ExchangeService exchangeService;

    @Before
    public void Set_Up() throws RequestException {
        final List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("Dolar", "USD", 4.0, LocalDate.now()));
        currencies.add(new Currency("Euro", "EUR", 2.0, LocalDate.now()));
        exchangeService = new ExchangeService(currencies);
    }

    @Test
    public void Should_Exchange_USD_For_EUR_With_Correct_Result() throws RequestException {
        // given
        final Money moneyToExchange = new Money(4, "USD");
        final String resultCurrency = "EUR";

        // when
        final Money result = exchangeService.exchange(moneyToExchange, resultCurrency);

        //then
        assertThat(result.getAmount()).isEqualTo(8);
        assertThat(result.getCurrencyCode()).isEqualTo(resultCurrency);
    }

    @Test
    public void Should_Exchange_USD_For_Polish_Money_With_Correct_Result() throws RequestException {
        // given
        final Money moneyToExchange = new Money(4, "USD");

        // when
        final Money result = exchangeService.exchangeToPolishMoney(moneyToExchange);

        //then
        assertThat(result.getAmount()).isEqualTo(16);
        assertThat(result.getCurrencyCode()).isEqualTo("PLN");
    }
}