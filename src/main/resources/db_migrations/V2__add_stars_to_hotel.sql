USE smart_city;

ALTER TABLE `hotel`
    ADD COLUMN IF NOT EXISTS (
        `stars` INT(6) DEFAULT NULL
        )
