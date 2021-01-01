package m.mirzaeyan.cart.domain;


import lombok.Getter;

@Getter
public enum TransactionStatus {

    firstRegister(1, "firstRegister", "ثبت اولیه"),
    success(1, "success", "موفق"),
    failed(2, "failed", "ناموفق");

    private final Integer index;
    private final String title;
    private final String persianTitle;

    TransactionStatus(Integer index, String title, String persianTitle) {
        this.index = index;
        this.title = title;
        this.persianTitle = persianTitle;
    }

}
