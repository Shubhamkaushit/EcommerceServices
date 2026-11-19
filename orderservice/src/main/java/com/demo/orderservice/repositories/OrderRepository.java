package com.demo.orderservice.repositories;

import com.demo.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}


/*
{
	"orderItemsDtoList": [
	{ "id": 1,  "skuCode": "iphone_15",      "price": 1200.50, "quantity": 1 },
	{ "id": 2,  "skuCode": "samsung_s24",    "price": 999.99,  "quantity": 2 },
	{ "id": 3,  "skuCode": "macbook_air_m3", "price": 1499.00, "quantity": 1 },
	{ "id": 4,  "skuCode": "dell_xps_13",    "price": 1299.75, "quantity": 1 },
	{ "id": 5,  "skuCode": "airpods_pro",    "price": 249.00,  "quantity": 3 },
	{ "id": 6,  "skuCode": "sony_wh1000xm5", "price": 399.99,  "quantity": 2 },
	{ "id": 7,  "skuCode": "ipad_pro_11",    "price": 999.00,  "quantity": 1 },
	{ "id": 8,  "skuCode": "logitech_mx3",   "price": 99.99,   "quantity": 4 },
	{ "id": 9,  "skuCode": "kindle_paper",   "price": 139.99,  "quantity": 2 },
	{ "id": 10, "skuCode": "gopro_hero12",   "price": 349.50,  "quantity": 1 },
	{ "id": 11, "skuCode": "apple_watch_s9", "price": 429.00,  "quantity": 1 },
	{ "id": 12, "skuCode": "jbl_flip_6",     "price": 129.95,  "quantity": 5 }
	]
}
*/

/*
{
	"orderItemsDtoList": [
	{ "skuCode": "oneplus_12",       "price": 799.00,  "quantity": 1 },
	{ "skuCode": "pixel_8_pro",      "price": 999.00,  "quantity": 2 },
	{ "skuCode": "lenovo_thinkpad",  "price": 1150.25, "quantity": 1 },
	{ "skuCode": "hp_spectre_x360",  "price": 1399.99, "quantity": 1 },
	{ "skuCode": "bose_qc_ultra",    "price": 429.00,  "quantity": 2 },
	{ "skuCode": "galaxy_tab_s9",    "price": 849.50,  "quantity": 1 },
	{ "skuCode": "anker_powerbank",  "price": 59.99,   "quantity": 6 },
	{ "skuCode": "razer_deathadder", "price": 69.00,   "quantity": 3 },
	{ "skuCode": "fitbit_charge_6",  "price": 159.95,  "quantity": 2 },
	{ "skuCode": "canon_eos_r50",    "price": 679.00,  "quantity": 1 },
	{ "skuCode": "samsung_ssd_1tb",  "price": 89.99,   "quantity": 4 },
	{ "skuCode": "tplink_router_ax", "price": 119.00,  "quantity": 2 }
	]
}
*/
