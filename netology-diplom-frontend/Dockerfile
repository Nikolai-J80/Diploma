# Используем официальный образ Node.js
FROM node:24-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем package.json и package-lock.json
COPY package*.json ./

# Устанавливаем зависимости
RUN npm install

# Копируем остальные файлы проекта
COPY . .

# Собираем проект (если требуется)
RUN npm run build

# Указываем порт, который будет использоваться
EXPOSE 8080

# Указываем команду для запуска
CMD ["npm", "start"]
