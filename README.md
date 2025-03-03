# newretail

1. 目錄結構如下

   1. crm: 為實作 **CRM 客戶分群與行銷功能**
   2. coupon-system: 為實作 **優惠券系統設計與使用**
   3. docker-compose.yml: 提供 Docker 設定檔，快速啟動系統所需的 MySQL、RabbitMQ、Redis 等環境。
   4. postman-test-api: 提供 Postman 測試 API 文檔範例

   ```
   - newretail-interview-homework
       - coupon-system
       - crm
       - docker-compose.yml
       - postman-test-api
   ```

2. 啟動方式：可以直接執行以下命令啟動所有服務：

   ```bash
   docker-compose up -d
   ```

   此命令會啟動所有必要的服務，包括 MySQL、RabbitMQ、Redis 以及兩個專案應用程式。

3. 專案說明

   1. 專案一：CRM 客戶分群與行銷功能

      1. 使用技術

         1. 後端：Java Spring Boot

         2. ORM: Mybatis

         3. Database: MySQL

         4. Version Control: Github

         5. CI/CD: Docker

         6. Others: RabbitMQ、Swagger、RESTful API

      2. 專案 URL：http://localhost:9001

      3. RESTful API 文件: 啟動 `docker-compose.yml` 後，可以在瀏覽器中訪問 http://localhost:9001/swagger-ui/index.html，查看 API 對應資訊。

      4. 功能實作

         1. 以消費金額與最後消費時間作為條件進行篩選（如「近 30 天消費金額超過 500 元的客戶」）。
            1. API：http://localhost:9001/customers/filteredByOrder
         2. 能夠對篩選出的客戶群發送行銷簡訊，簡訊範本需支援動態變數（如客戶姓名、消費金額）。
            1. API：http://localhost:9001/sms/send

      5. 資料庫結構

         - `customer` 表格

         | 欄位名稱     | 資料型態     | 描述                         |
         | ------------ | ------------ | ---------------------------- |
         | `id`         | INT          | 顧客ID，主鍵，AUTO_INCREMENT |
         | `first_name` | NVARCHAR(50) | 顧客的名字                   |
         | `last_name`  | NVARCHAR(50) | 顧客的姓氏                   |
         | `full_name`  | VARCHAR(100) | 顧客的全名                   |
         | `phone`      | VARCHAR(50)  | 顧客的電話                   |
         | `email`      | VARCHAR(200) | 顧客的電子郵件               |
         | `created_at` | DATETIME     | 顧客建立時間                 |
         | `updated_at` | DATETIME     | 顧客更新時間                 |

         - `order` 表格

         | 欄位名稱      | 資料型態      | 描述                                      |
         | ------------- | ------------- | ----------------------------------------- |
         | `id`          | INT           | 訂單ID，主鍵，AUTO_INCREMENT              |
         | `customer_id` | INT           | 顧客ID，外鍵，參照 `customer` 表格的 `id` |
         | `amount`      | DECIMAL(10,2) | 訂單金額                                  |
         | `order_date`  | DATETIME      | 訂單日期                                  |
         | `created_at`  | DATETIME      | 訂單建立時間                              |
         | `updated_at`  | DATETIME      | 訂單更新時間                              |

         - `message` 表格

          | 欄位名稱      | 資料型態      | 描述                                      |
          | ------------- | ------------- | ----------------------------------------- |
          | `id`          | INT           | 訊息ID，主鍵，AUTO_INCREMENT              |
          | `customer_id` | INT           | 顧客ID，外鍵，參照 `customer` 表格的 `id` |
          | `message`     | NVARCHAR(500) | 訊息內容                                  |
          | `sent_at`     | DATETIME      | 發送時間                                  |
          | `status`      | INT           | 訊息狀態                                  |
          | `created_at`  | DATETIME      | 訊息建立時間                              |

         2. Table Relationships
            1. Order 會關聯到 Customer：用以紀錄顧客有哪些訂單，一對多
            2. Message 會關連到 Customer： 用以紀錄傳送簡訊紀錄，一對多

      6. 使用到的額外技術

         1. RabbitMQ：利於傳送簡訊時，以非同步的方式傳送簡訊，並回寫紀錄到 Database

   2. 專案二：優惠券系統設計與使用

      1. 使用技術

         1. 後端：Java Spring Boot

         2. ORM: Mybatis

         3. Database: MySQL

         4. Version Control: Github

         5. CI/CD: Docker

         6. Others: RabbitMQ、Redis、Swagger、RESTful API

      2. 專案 URL：http://localhost:9002

      3. RESTful API 文件: 啟動 `docker-compose.yml` 後，可以在瀏覽器中訪問 http://localhost:9002/swagger-ui/index.html，查看 API 對應資訊。

      4. 功能實作

         1. 用戶可領取特定優惠券（如滿減券、折扣券），每張優惠券數量有限。
            1. API：
               1. http://localhost:9002/coupon/reset: 清除 redis 暫存資料
               2. http://localhost:9002/coupon/initialize: 建立可領取的優惠券，緩存至 redis 
               3. http://localhost:9002/coupon/getCoupon: 領取優惠券
         2. 用戶在優惠券的有效期內使用，系統需驗證優惠券的可用性（例如過期或已使用則無法使用）。
            1. API：http://localhost:9002/userCoupon/redeem
         3. 提供查詢介面或是API，顯示使用者所有的優惠券狀態（未使用、已使用、已過期）
            1. 對應API：http://localhost:9002/users/{userId}/coupons?status=1
               1. status: (選擇性參數)
                  1. 0: 未使用
                  2. 1: 已使用
                  3. 2: 已過期

      5. 資料庫結構

         -  **coupon 表格**

         | 欄位名稱   | 類型          | 描述                           |
         | ---------- | ------------- | ------------------------------ |
         | id         | INT           | 主鍵，AUTO_INCREMENT           |
         | name       | NVARCHAR(200) | 優惠券名稱，不能為空           |
         | quantity   | INT           | 優惠券數量，不能為空           |
         | type       | INT           | 優惠券類型，不能為空           |
         | start_date | DATETIME      | 優惠券開始時間，不能為空       |
         | end_date   | DATETIME      | 優惠券結束時間，不能為空       |
         | created_at | TIMESTAMP     | 優惠券建立時間，默認為當前時間 |

         -  **user 表格**

         | 欄位名稱 | 類型         | 描述                 |
         | -------- | ------------ | -------------------- |
         | id       | INT          | 主鍵，AUTO_INCREMENT |
         | name     | NVARCHAR(50) | 使用者名稱，不能為空 |

         -  **user_coupon 表格**

         | 欄位名稱   | 類型      | 描述                                            |
         | ---------- | --------- | ----------------------------------------------- |
         | id         | INT       | 主鍵，AUTO_INCREMENT                            |
         | user_id    | INT       | 外鍵，引用 `user` 表格的 id                     |
         | coupon_id  | INT       | 外鍵，引用 `coupon` 表格的 id                   |
         | status     | INT       | 優惠券狀態<br />  0: 未使用 1: 已使用 2: 已過期 |
         | used_at    | DATETIME  | 使用時間，若未使用則為 NULL                     |
         | claimed_at | TIMESTAMP | 領取時間，默認為當前時間                        |

         - **coupon_log 表格**

         | 欄位名稱       | 類型      | 描述                                         |
         | -------------- | --------- | -------------------------------------------- |
         | id             | INT       | 主鍵，AUTO_INCREMENT                         |
         | user_id        | INT       | 外鍵，引用 `user` 表格的 id                  |
         | coupon_id      | INT       | 外鍵，引用 `coupon` 表格的 id                |
         | operation_type | INT       | 操作類型<br />0: 新增 1:刪除 2: 更新 3: 查詢 |
         | description    | TEXT      | 操作描述                                     |
         | created_at     | TIMESTAMP | 日誌建立時間，默認為當前時間                 |

         2. Table Relationships
            1. user_coupon: 會關連到 user 以及 coupon，多對多關係
            2. coupon_log: 會關連到 user 以及 coupon，多對多關係

      6. 使用到的額外技術

         1. Redis：取得優惠券併發處理，避免超發的情形，以 redis 設定到期時間，達到 redis lock 效果，處理併發的情況
         2. RabbitMQ：利於 redis 取得優惠卷後，以非同步的方式，將資料寫入資料庫