package com.lucasengcomp.junit.blog.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Editor {
    private Long id;
    private String name;
    private String email;
    private BigDecimal pricePerWord;
    private boolean premium;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Editor() {

    }

    public Editor(String name, String email, BigDecimal pricePerWord, boolean premium) {
        this(null, name, email, pricePerWord, premium);
    }

    public Editor(Long id, String name, String email, BigDecimal pricePerWord, boolean premium) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        Objects.requireNonNull(pricePerWord);
        this.id = id;
        this.name = name;
        this.email = email;
        this.pricePerWord = pricePerWord;
        this.premium = premium;
    }

    public void updateWithData(Editor editor) {
        Objects.requireNonNull(editor);
        this.name = editor.name;
        this.email = editor.email;
        this.pricePerWord = editor.pricePerWord;
        this.premium = editor.premium;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getPricePerWord() {
        return pricePerWord;
    }

    public void setPricePerWord(BigDecimal pricePerWord) {
        this.pricePerWord = pricePerWord;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editor editor = (Editor) o;
        return Objects.equals(id, editor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {
        private Long id;
        private String name;
        private String email;
        private BigDecimal pricePerWord;
        private boolean premium;

        public Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPaymentValueWithWord(BigDecimal pricePerWord) {
            this.pricePerWord = pricePerWord;
            return this;
        }

        public Builder withPremium(boolean premium) {
            this.premium = premium;
            return this;
        }

        public Editor build() {
            return new Editor(
                    this.id,
                    this.name,
                    this.email,
                    this.pricePerWord,
                    this.premium
            );
        }
    }
}
