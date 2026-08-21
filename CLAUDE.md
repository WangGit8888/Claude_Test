# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

`bid-system` (标段每日填报系统) — a Java 17 / Spring Boot 3.3.6 web app built with Maven. It is a mix of a real CRUD application (bid-section daily report, BPM process) and a collection of learning demos for design patterns, JUC concurrency, proxies, and JVM internals. The database is MySQL 8, accessed through MyBatis-Plus 3.5.5.

Note: `pomodoro.py`, `requirements.txt`, `run.bat`, and `__pycache__/` are stale Python artifacts unrelated to the Java app — ignore them.

## Commands

```bash
mvn spring-boot:run            # run the app (listens on port 8881)
mvn test                       # run tests (only ApplicationTests#contextLoads exists)
mvn -Dtest=ApplicationTests test   # run a single test class
mvn clean package              # build executable jar into target/ (used by Dockerfile)
```

No linter is configured. The OpenAPI/Swagger UI is served by springdoc at `/swagger-ui.html`. The app expects MySQL at `localhost:3306` (root/123456) and JSP views under `/WEB-INF/page/`.

## Architecture

The source root is `com.example`; `Application.java` is the entrypoint and scans mappers via `@MapperScan("com.example.mapper")`.

- **`common/`** — shared infrastructure used across CRUD modules:
  - `model/ApiResponseBody<T>` — standard `{code, message, data}` response envelope (`success()` / `error()`).
  - `model/PageResult<T>` — pagination result built by `PageResult.of(...)`.
  - `model/BaseClass` — intended base entity with audit columns (`id` via snowflake `ASSIGN_ID`, `create_by`/`create_time`/`update_by`/`update_time`, `del_flag` marked `@TableLogic`). Some entities (e.g. `BidSectionDailyReport`) define these fields inline instead of extending it.
  - `dto/RequestDTO` + `dto/Condition` — fixed pagination+filter query input. `RequestDTO` carries `pageNum`/`pageSize`/`condition`; `Condition` holds filter fields (the `keyword` field is the convention for a fuzzy match).
  - `util/SnowflakeIdGenerator` — standalone snowflake ID generator (alternative to MyBatis-Plus `ASSIGN_ID`).
- **`config/MybatisPlusConfig`** — registers the pagination interceptor (MySQL).
- **`bpm/`** — BPM process module (`controller` + `entity`), the reference for a standard CRUD feature.
- **`sjms/`** (设计模式 / design patterns) — two worked pattern demos, each in its own sub-package:
  - `clms/` — **Strategy pattern** for defect handling. `DefectHandler` is the strategy interface; `DefectHandlerFactory` builds a `Map<equipmentType, handler>` by autowiring `List<DefectHandler>`; `DefectServiceV2` is the orchestrator.
  - `zrlms/` — **Chain of Responsibility** for intern evaluation. `InternEvaluationChainBuilder` autowires `List<InternEvaluationHandler>`, sorts by `@Order`, and links them via `setNext`. `run/InternController` exposes `/api/intern/evaluate`.
- **`juc/`** — Java concurrency demos (`CompletableFuture*`, `CountDownLatch`, `CyclicBarrier`, `Semaphore`, `Interrupt`, `Volatile`), each a self-contained `main` class.
- **`proxy/`, `jvm/`, `test01/`** — small demos for JDK/CGLib dynamic proxies, JVM visibility, and GC/thread experiments.

## Code-generation conventions

Several root-level `.md` files encode how new CRUD modules should be written. Follow these when adding features:

- **`mybatis_plaus.md`** — standard module layout under one package: `controller`, `mapper`, `entity`, `service`/`serviceImpl` (`dto`/`vo` only when needed). Business logic goes in the `serviceImpl`, never in the controller. Entities use Lombok `@Data`. The service layer must provide: a paginated query whose input is fixed to `com.example.common.dto.RequestDTO` (with a `buildLambdaQueryWrapper` method that constructs filter conditions, initially empty and extended later), a single create/update via MyBatis-Plus `saveOrUpdate`, and a batch delete.
- **`create_table.md`** — every table gets the common columns: `id` BIGINT PK, `dele_flag` (0/1 delete flag), `create_by`, `create_time`, `update_by`, `update_time` (unless stated otherwise).
- **`import_export.md`** — import/export must use EasyExcel; export reuses the service's `buildLambdaQueryWrapper` for conditional export.
- **`java语言规范`** — code must follow the Alibaba Java Coding Guidelines (华山版).

Gotcha: `application.yml` sets the MyBatis-Plus logic-delete field to `deleteFlag`, but `BaseClass` declares it as `delFlag` (`@TableLogic`) and table DDLs use `dele_flag` — these are not currently in agreement; check the target entity/table when relying on logic delete.
