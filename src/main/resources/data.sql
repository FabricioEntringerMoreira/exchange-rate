DROP TABLE IF EXISTS exchange;

DROP TABLE IF EXISTS costs;

CREATE TABLE exchange (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  origin_currency VARCHAR(3) NOT NULL,
  target_currency VARCHAR(3) NOT NULL,
  rate DECIMAL NOT NULL,
  amount DECIMAL NOT NULL,
  value VARCHAR(3) NOT NULL,
  date_time_rate DATE DEFAULT NULL
);
