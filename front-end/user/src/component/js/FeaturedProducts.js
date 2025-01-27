import React, { useEffect, useState } from "react";
import ProductCard from "./ProductCard";
import { useSelector, useDispatch } from "react-redux";
import { productListSelector } from "../../redux/selectors";

import { fetchFeaturedProducts } from "../../api/product";
import { fetchProductList } from "../../redux/slices/productSlice";

import AxiosClient from "../../axios/AxiosClient";
import { raiseErrorMessages } from "../../utils/errorUtil";
import { typeImplementation } from "@testing-library/user-event/dist/type/typeImplementation";

const FeaturedProducts = (props) => {
    const [productList, setProductList] = useState([]);
    // const productList = useSelector(productListSelector);

    useEffect(() => {
        async function fetchData() {
            let rs = await fetchFeaturedProducts();
            setProductList(rs.content);
        }

        fetchData();
    }, []);

    return (
        <div className="container-fluid pt-5">
            <div className="text-center mb-4">
                <h2 className="section-title px-5"><span className="px-2">Featured</span></h2>
            </div>
            <div className="row px-xl-5 pb-3">
                {
                    productList.map(product => {
                        return (
                            <div className="col-lg-3 col-md-6 col-sm-12 pb-1">
                                <ProductCard key={product.id} productId={product.id} name={product.name} price={product.price} img={product.thumbnail} /> 
                            </div>
                        )
                    })
                }
            </div>
        </div>
    );
}

export default FeaturedProducts;