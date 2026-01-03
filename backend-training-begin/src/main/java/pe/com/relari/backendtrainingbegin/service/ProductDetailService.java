package pe.com.relari.backendtrainingbegin.service;

import pe.com.relari.backendtrainingbegin.dao.proxy.AccountApi;
import pe.com.relari.backendtrainingbegin.model.external.Product;
import pe.com.relari.backendtrainingbegin.model.external.ProductDetail;
import pe.com.relari.backendtrainingbegin.dao.proxy.NotificationApi;
import pe.com.relari.backendtrainingbegin.dao.proxy.ProductApi;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductDetailService {

    public static void main(String[] args) {

        processProductDetail().subscribe();

    }

    private static Observable<ProductDetail> processProductDetail() {
        return ProductApi.getProducts()
                .flatMapSingle(ProductDetailService::getAccountAndBuildProductDetail)
                .filter(ProductDetail::getStatus)
                .toList()
                .flatMapObservable(productDetails ->
                        NotificationApi.sendNotification(productDetails)
                                .andThen(Observable.fromIterable(productDetails))
                )
                .doOnSubscribe(disposable -> log.info("Microservice ProductDetail Starting"))
                .doOnNext(productDetail -> log.info(productDetail.toString()))
                .doOnComplete(() -> log.info("Microservice ProductDetail Complete"))
                .doOnTerminate(() -> log.info("Microservice ProductDetail Terminate"));
    }

    private static Single<ProductDetail> getAccountAndBuildProductDetail(Product product) {
        return AccountApi.searchAccountByProductId(product.getId())
                .map(account -> ProductDetail.builder()
                        .productId(product.getId())
                        .description(product.getDescription())
                        .status(product.getStatus())
                        .price(account.getPrice())
                        .stock(account.getStock())
                        .build()
                );
    }

}
