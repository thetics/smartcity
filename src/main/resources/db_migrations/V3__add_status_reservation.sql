USE smart_city;
ALTER TABLE reservation
ADD COLUMN IF NOT EXISTS (
    `status` ENUM ('CONFIRMED', 'UNCONFIRMED', 'PENDING') NOT NULL DEFAULT 'UNCONFIRMED'
    )